package org.cg.rooster.redshift;

import java.util.LinkedList;
import java.util.List;

import org.cg.rooster.core.Condition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**
 * Example usage of RedshiftJdbcDataRepository
 * @author WZ
 *
 */
public class TestRedshiftJdbcDataRepository {

	private UserDataRepository dataRepository;

	@Before
	public void setUp() throws Exception {
		dataRepository = new UserDataRepository();
	}

	@Test
	public void testExists() {
		boolean isExist = dataRepository.exists(new Object[] {"da4b602e-77ec-4a94-b593-6f41b5636727"});
		Assert.assertTrue(isExist);
	}

	@Test
	public void testCount() {
		long count = dataRepository.count();
		System.out.println(count);
		//Assert.assertTrue(count == 100);
	}

	@Test
	public void testFindOne() {
		User e = dataRepository.find(new Object[] {"da4b602e-77ec-4a94-b593-6f41b5636727"});
		System.out.println(e);
		Assert.assertNotNull(e);
	}

	@Test
	public void testFindAll() {
		List<User> list = (List<User>) dataRepository.findAll();
		Assert.assertTrue(!list.isEmpty());
	}

	@Test
	public void testFindAllWithSort() {
		List<User> list = (List<User>) dataRepository.findAll(
				new Sort(new Order(Direction.DESC, "id"), 
						 new Order(Direction.ASC, "lastinvite")));
		Assert.assertTrue(!list.isEmpty());
	}

	@Test
	public void testFindAllWithLimit() {
		List<User> list = (List<User>) dataRepository.findAll(2);
		Assert.assertTrue(!list.isEmpty());
		Assert.assertTrue(list.size()==2);
	}

	@Test
	public void testFindAllWithLimitAndSort() {
		List<User> list = (List<User>) dataRepository.findAll(
				new Sort(new Order(Direction.DESC, "id"), 
						 new Order(Direction.ASC, "lastinvite"))
				         , 2);
		Assert.assertTrue(!list.isEmpty());
		Assert.assertTrue(list.size()==2);
	}

	@Test
	public void testFind() {
		List<Condition> conditions = new LinkedList<Condition>();
		conditions.add(new Condition("forest", RedshiftConditionOperator.EQUAL, "c.com"));
		Condition c1 = new Condition("status", RedshiftConditionOperator.EQUAL, "Active");
		Condition c2 = new Condition("status", RedshiftConditionOperator.EQUAL, "Suspended");
		conditions.add(new Condition( c1, RedshiftConditionOperator.OR, c2 ) );
		List<User> list = (List<User>) dataRepository.find(conditions);
		Assert.assertTrue(!list.isEmpty());

		for (User e : list) {
			System.out.println(e);
		}
	}

	@Test
	public void testFindWithSort() {
		List<Condition> conditions = new LinkedList<Condition>();
		conditions.add(new Condition("forest", RedshiftConditionOperator.EQUAL, "c.com"));
		Condition c1 = new Condition("status", RedshiftConditionOperator.EQUAL, "Active");
		Condition c2 = new Condition("status", RedshiftConditionOperator.EQUAL, "Suspended");
		conditions.add(new Condition( c1, RedshiftConditionOperator.OR, c2 ) );
		List<User> list = (List<User>) dataRepository.find(conditions, new Sort(new Order(Direction.DESC, "lastlogin")));
		Assert.assertTrue(!list.isEmpty());

		for (User e : list) {
			System.out.println(e);
		}
	}

	@Test
	public void testFindWithLimit() {
		List<Condition> conditions = new LinkedList<Condition>();
		conditions.add(new Condition("forest", RedshiftConditionOperator.EQUAL, "c.com"));
		Condition c1 = new Condition("status", RedshiftConditionOperator.EQUAL, "Active");
		Condition c2 = new Condition("status", RedshiftConditionOperator.EQUAL, "Suspended");
		conditions.add(new Condition( c1, RedshiftConditionOperator.OR, c2 ) );
		List<User> list = (List<User>) dataRepository.find(conditions, 2);
		Assert.assertTrue(!list.isEmpty());

		for (User e : list) {
			System.out.println(e);
		}
	}

	@Test
	public void testFindWithLimitAndSort() {
		List<Condition> conditions = new LinkedList<Condition>();
		conditions.add(new Condition("forest", RedshiftConditionOperator.EQUAL, "c.com"));
		Condition c1 = new Condition("status", RedshiftConditionOperator.EQUAL, "Active");
		Condition c2 = new Condition("status", RedshiftConditionOperator.EQUAL, "Suspended");
		conditions.add(new Condition( c1, RedshiftConditionOperator.OR, c2 ) );
		List<User> list = (List<User>) dataRepository.find(conditions, new Sort(new Order(Direction.DESC, "lastlogin")), 100);
		Assert.assertTrue(!list.isEmpty());

		for (User e : list) {
			System.out.println(e);
		}
	}

}