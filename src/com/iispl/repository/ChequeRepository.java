package com.iispl.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.iispl.enums.ChequeStatus;
import com.iispl.model.Cheque;

public class ChequeRepository {

	static List<Cheque> chequeList = null;
	
	static {
		
		chequeList = new ArrayList<Cheque>();
		
		chequeList.add(new Cheque("CHQ1001","ACC1001",new BigDecimal("25000.00"),LocalDate.of(2026,7,1),"Hyderabad",ChequeStatus.PENDING));

		chequeList.add(new Cheque("CHQ1002","ACC1002",new BigDecimal("100000.00"),LocalDate.of(2026,7,2),"Chennai",ChequeStatus.PENDING));

		chequeList.add(new Cheque("CHQ1003","ACC1005",new BigDecimal("500000.00"),LocalDate.of(2026,7,3),"Bangalore",ChequeStatus.PENDING));

		chequeList.add(new Cheque("CHQ1004","ACC1007",new BigDecimal("500000.00"),LocalDate.of(2026,7,4),"Hyderabad",ChequeStatus.PENDING));

		chequeList.add(new Cheque("CHQ1005","ACC1003",new BigDecimal("1500.00"),LocalDate.of(2026,7,5),"Vijayawada",ChequeStatus.PENDING));

		chequeList.add(new Cheque("","ACC1001",new BigDecimal("10000.00"),LocalDate.of(2026,7,6),"Hyderabad",ChequeStatus.PENDING));

		chequeList.add(new Cheque("CHQ1002","ACC1006",new BigDecimal("45000.00"),LocalDate.of(2026,7,6),"Chennai",ChequeStatus.PENDING));

		chequeList.add(new Cheque("CHQ1007","",new BigDecimal("25000.00"),LocalDate.of(2026,7,6),"Hyderabad",ChequeStatus.PENDING));

		chequeList.add(new Cheque("CHQ1008","ACC1002",BigDecimal.ZERO,LocalDate.of(2026,7,6),"Bangalore",ChequeStatus.PENDING));

		chequeList.add(new Cheque("CHQ1009","ACC1003",new BigDecimal("-500.00"),LocalDate.of(2026,7,6),"Hyderabad",ChequeStatus.PENDING));

		chequeList.add(new Cheque("CHQ1010","ACC1005",new BigDecimal("75000.00"),LocalDate.now().plusDays(5),"Chennai",ChequeStatus.PENDING));

		chequeList.add(new Cheque("CHQ1011","ACC1001",new BigDecimal("30000.00"),LocalDate.of(2026,7,6),"",ChequeStatus.PENDING));

		chequeList.add(new Cheque("CHQ1012","ACC1004",new BigDecimal("5000.00"),LocalDate.of(2026,7,6),"Hyderabad",null));

	} 
	
	public static List<Cheque> getChequeList() {
		return chequeList;
	}
	
}
