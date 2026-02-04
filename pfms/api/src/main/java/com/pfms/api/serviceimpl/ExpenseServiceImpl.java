package com.pfms.api.serviceimpl;

import com.pfms.api.bean.Expense;
import com.pfms.api.bean.User;
import com.pfms.api.dto.ExpenseDTO;
import com.pfms.api.repository.ExpenseRepository;
import com.pfms.api.repository.UserRepository;
import com.pfms.api.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	  @Autowired
	    private ExpenseRepository expenseRepository;

	    @Autowired
	    private UserRepository userRepository;
	    
	    @Override
	    @CacheEvict(value = "expenses", key = "#p0")
	    public Expense addExpense(Long userId, Expense expense) {
	        User user = userRepository.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	        expense.setUser(user);
	        return expenseRepository.save(expense);
	    }

	    @Override
	    @Cacheable(value = "expenses", key = "#p0")
	    public List<ExpenseDTO> getExpensesByUser(Long userId) {
	    	System.out.println("🔥 FETCHING FROM DATABASE for userId = " + userId);
	        return expenseRepository.findByUserId(userId)
	                .stream()
	                .map(this::toDto)
	                .toList();
	    }

	    @Override
	    public Expense getExpenseById(Long expenseId) {
	        return expenseRepository.findById(expenseId)
	                .orElseThrow(() -> new RuntimeException("Expense not found"));
	    }

	    @Override
	    @CacheEvict(value = "expenses", key = "#p0")
	    public Expense updateExpense(Long userId, Long expenseId, Expense expense) {
	        Expense existing = getExpenseById(expenseId);
	        existing.setCategory(expense.getCategory());
	        existing.setAmount(expense.getAmount());
	        existing.setDescription(expense.getDescription());
	        existing.setDate(expense.getDate());
	        return expenseRepository.save(existing);
	    }

	    @Override
	    @CacheEvict(value = "expenses", key = "#p0")
	    public void deleteExpense(Long userId, Long expenseId) {
	        expenseRepository.deleteById(expenseId);
	    }
	    
	    
	    private ExpenseDTO toDto(Expense e) {
	        ExpenseDTO dto = new ExpenseDTO();
	        dto.setId(e.getId());
	        dto.setUserId(e.getUser().getId()); // SAFE: entity from DB
	        dto.setCategory(e.getCategory());
	        dto.setAmount(e.getAmount());
	        dto.setDescription(e.getDescription());
	        dto.setDate(e.getDate());
	        return dto;
	    }

}
