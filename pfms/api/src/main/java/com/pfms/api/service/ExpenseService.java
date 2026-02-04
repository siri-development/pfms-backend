package com.pfms.api.service;

import java.util.List;

import com.pfms.api.bean.Expense;
import com.pfms.api.dto.ExpenseDTO;

public interface ExpenseService {
	Expense addExpense(Long userId, Expense expense);

	List<ExpenseDTO> getExpensesByUser(Long userId);

	Expense getExpenseById(Long expenseId);

	Expense updateExpense(Long userId, Long expenseId, Expense expense);

	void deleteExpense(Long userId, Long expenseId);
}
