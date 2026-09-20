package com.SpringBootTest.JunitTesting;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class JunitTestingApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
//	@Disabled
//	@DisplayName("myTest1")

	void test1()
	{
		int a = 7;
		int b = 14;
//		int res = a+b;
//		Assertions.assertEquals(9,res);
		Assertions.assertThat(addTwoNumbers(a,b))
				.isEqualTo(21)
				.isCloseTo(10, Offset.offset(15));


	}
	@Test
	void testDivideTwoNumbers_whenDenominatorIsZero_AndThrowsArithmeticException()
	{
		Assertions.assertThatThrownBy(()->divideTwoNumbers(2,0))
				.isInstanceOf(ArithmeticException.class)
				.hasMessage("/ by zero");
	}

	int addTwoNumbers(int a,int b)
	{
		return a+b;
	}

	double divideTwoNumbers(int a,int b)
	{
		try
		{
			return a/b;
		}
		catch(ArithmeticException e)
		{
			log.error("Arithmetic Exception");
			throw new ArithmeticException(e.getLocalizedMessage());
		}
	}

}
