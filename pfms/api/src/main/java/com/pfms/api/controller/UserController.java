package com.pfms.api.controller;

import com.pfms.api.bean.Expense;
import com.pfms.api.dto.ExpenseDTO;
import com.pfms.api.repository.UserRepository;
import com.pfms.api.service.ExpenseService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/expenses")
public class UserController {

	    @Autowired
	    private ExpenseService expenseService;
	    
	    @Autowired
	    private UserRepository userRepository;

	    @PostMapping
	    public ResponseEntity<ExpenseDTO> addExpense(@RequestBody ExpenseDTO expenseDTO, Authentication auth) {
	        Long userId = getUserIdFromAuth(auth);
	        expenseDTO.setUserId(userId);
	        Expense saved = expenseService.addExpense(userId, mapToEntity(expenseDTO));
	        return ResponseEntity.ok(mapToDto(saved));
	    }

	    @GetMapping
	    public ResponseEntity<List<ExpenseDTO>> getExpenses(Authentication auth) {
	        Long userId = getUserIdFromAuth(auth);
	        return ResponseEntity.ok(expenseService.getExpensesByUser(userId));
	    }


	    @PutMapping("/{id}")
	    public ResponseEntity<ExpenseDTO> updateExpense(
	            @PathVariable("id") Long id,
	            @RequestBody ExpenseDTO expenseDTO,
	            Authentication auth) {
	        Long userId = getUserIdFromAuth(auth);
	        Expense updated = expenseService.updateExpense(userId, id, mapToEntity(expenseDTO));
	        return ResponseEntity.ok(mapToDto(updated));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteExpense(
	            @PathVariable("id") Long expenseId,
	            Authentication auth) {

	        Long userId = getUserIdFromAuth(auth);
	        expenseService.deleteExpense(userId, expenseId);
	        return ResponseEntity.noContent().build(); // 204
	    }

	    private Long getUserIdFromAuth(Authentication auth) {
	        String email = auth.getName();  // email from JWT
	        return userRepository.findByEmail(email)
	                .orElseThrow(() -> new RuntimeException("User not found"))
	                .getId();
	    }

	    private ExpenseDTO mapToDto(Expense e) {
	        ExpenseDTO dto = new ExpenseDTO();
	        dto.setId(e.getId());
	        dto.setCategory(e.getCategory());
	        dto.setAmount(e.getAmount());
	        dto.setDescription(e.getDescription());
	        dto.setDate(e.getDate());
	        dto.setUserId(e.getUser().getId());
	        return dto;
	    }

	    private Expense mapToEntity(ExpenseDTO dto) {
	        Expense e = new Expense();
	        e.setId(dto.getId());
	        e.setCategory(dto.getCategory());
	        e.setAmount(dto.getAmount());
	        e.setDescription(dto.getDescription());
	        e.setDate(dto.getDate());
	        return e;
	    }
}
