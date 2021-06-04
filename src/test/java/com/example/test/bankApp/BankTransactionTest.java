package com.example.test.bankApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.example.service.BankAccountService;
import com.example.service.UserService;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class BankTransactionTest {
		
		private BankAccountService bas;
		private UserService us;
		
		//This annotation allow this method to run before the class is instantiated
		@BeforeAll // in JUnit 4 and below this annotation was called BeforeClass
		public static void setUpBeforeClass() {
			System.out.println("-----------Before Class-------------");
		}
		
		//This annotation will allow this method to execute after all of the test are run, used for closing resources that are set up in the
		//mehtod with the @BeforeAll
		@AfterAll //  in JUnit 4 this was called AfterClass
		public static void tearDownAfterClass() {
			System.out.println("-----------After Class--------------");
		}
		
		// this annotation will allow the method to execute before each individual test.
		@BeforeEach //this used be called Before in jUnit 4
		public void setUp() {
			System.out.println("-----------Before Method------------");
			calc = new Calculator();
		}
		
		//This annotation will allow the method to execute after each individual test. It is used to close resources opened in the 
		// the method with BeforeEach
		@AfterEach
		public void tearDown() {
			System.out.println("-----------After Method------------");
		}
		
		@Test
		@Order(1)
		public void addNagativeNumberTest() {
			System.out.println("in add test");
			assertEquals(1, calc.add(-3, 4), "This is the -3+4 test"); //assertEquals(expectedValue, actualValue, fail string)
		}
		
		@Test
		@Order(2)
		public void multiplyPositiveNumberTest() {
			System.out.println("in multiple test");
			assertEquals(10, calc.multiple(2, 5), "the 2 * 5 test");
			assertEquals(10, calc.multiple(5, 2), "the 5 * 2 test");
		}
		
//		@Test(expected = IllegalArgumentException.class) Junit 4 testing for exceptions
		@Test
		@Order(3)
		public void divisionExecptionThrownTest() { //this is a negative test
			assertThrows(IllegalArgumentException.class, () -> calc.division(1,0));
			System.out.println("division exception");
		}
		
		@Test
		@Order(4)
		public void divisionExecptionNotThrownTest() { // this is a positive test
//			assertThrows(NullPointerException.class, () -> calc.division(1,0));
			assertEquals(2, calc.division(4, 2), "4/2 division test");
			System.out.println("divsion no exception");
		}
		
		

	}


}
//package com.example.eval.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.example.dao.CreatureCardDaoImpl;
//import com.example.model.CreatureCard;
//import com.example.service.CreatureCardService;
//
//public class CreatureCardServiceTest {
//	
//	@Mock
//	private CreatureCardDaoImpl fakeDao;
//	private CreatureCardService ccServ /* = new CreatureCardService(fakeDao) */;
//	private CreatureCard card;
//	private CreatureCard card2;
//	
//	@BeforeEach
//	public void setUp() {
//		MockitoAnnotations.initMocks(this); //  will tell mackito to create an instance of ever field member marked by @Mock
//		ccServ = new CreatureCardService(fakeDao);
//		 card2 = new CreatureCard();
//		card = new CreatureCard("Jace", 4, "Elder Dragon", 4, 4, new ArrayList<String>(Arrays.asList("Blue", "Black", "Red")));
//		when(fakeDao.getByName("Jace")).thenReturn(card); //this will override the method getByName, and will only perform the 
//		//return action
//		when(fakeDao.getByName("nope")).thenReturn(card2);
//		when(fakeDao.getAll()).thenReturn(new ArrayList<CreatureCard>(Arrays.asList(card)));
//		doNothing().when(fakeDao).insert(card2); // how to mock void return type methods
//		
//	}
//	
//	@Test
//	public void getCardByNameSuccess() {
//		assertEquals("Jace", ccServ.getCreatureCardByName("Jace").getName(), "Creature card service get name success");
//	}
//	
//	@Test
//	public void getCardByNameFailure() {
//		assertThrows(NullPointerException.class, ()-> ccServ.getCreatureCardByName("nope"));
//	}
//	
//	@Test
//	public void insertCardSuccess() {
//		assertEquals("Card added", ccServ.insertCreatureCard(card2));
//	}
//
//}

