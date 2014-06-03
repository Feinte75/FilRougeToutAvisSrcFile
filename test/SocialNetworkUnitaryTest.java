package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import avis.SocialNetwork;

public class SocialNetworkUnitaryTest {

	SocialNetwork sn;
	
	@Before
	public void setUp() {
		sn = new SocialNetwork();
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testNbMembers() {
		
		assertEquals(0, sn.nbMembers());
		try {
			sn.addMember("pseudo", "test", "Test");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		assertEquals(1, sn.nbMembers());
	}

	@Test
	public void testNbFilms() {
		
		assertEquals(0, sn.nbFilms());
		
		try {
			sn.addMember("Pseudo", "test", "Test");
			sn.addItemFilm("Pseudo", "test", "test", "test", "test", "test", 120);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		assertEquals(1, sn.nbFilms());
	}

	@Test
	public void testNbBooks() {
		
		assertEquals(0, sn.nbBooks());

		try {
			sn.addMember("Pseudo", "test", "Test");
			sn.addItemBook("Pseudo", "test", "test", "test", "test", 120);
		} catch (Exception e) {

			e.printStackTrace();
		}

		assertEquals(1, sn.nbBooks());
	}

	@Test
	public void testAddMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddItemFilm() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddItemBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testConsultItems() {
		fail("Not yet implemented");
	}

	@Test
	public void testReviewItemFilm() {
		fail("Not yet implemented");
	}

	@Test
	public void testReviewItemBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testReviewOpinionBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testReviewOpinionFilm() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
