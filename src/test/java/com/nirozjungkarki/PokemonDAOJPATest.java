package com.nirozjungkarki;

import static org.fest.assertions.Assertions.assertThat;

import java.sql.Connection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PokemonDAOJPATest {

	private static EntityManager entityManager;
	private static FlatXmlDataSet dataset;
	private static DatabaseConnection dbUnitConnection;
	private static EntityManagerFactory entityManagerFactory;

	private PokemonDAO dao = new PokemonDAOJPA(entityManager);

	@BeforeClass
	public static void initTestFixture() throws Exception {
		// Get the entity manager for the tests.
		entityManagerFactory = Persistence.createEntityManagerFactory("pokebattlePUTest");
		entityManager = entityManagerFactory.createEntityManager();

		Connection connection = ((EntityManagerImpl) (entityManager.getDelegate())).getServerSession().getAccessor()
				.getConnection();

		dbUnitConnection = new DatabaseConnection(connection);
		// Loads the data set from a file
		dataset = new FlatXmlDataSetBuilder()
				.build(Thread.currentThread().getContextClassLoader().getResourceAsStream("pokemonDataset.xml"));
	}

	@AfterClass
	public static void finishTestFixture() throws Exception {
		entityManager.close();
		entityManagerFactory.close();
	}

	@Before
	public void setUp() throws Exception {
		// Clean the data from previous test and insert new data test.
		DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
	}

	@Test
	public void testFindByType() throws Exception {
		List<Pokemon> pokemons = dao.findByType(Type.ELECTRIC);
		assertThat(pokemons.get(0).getName()).isEqualTo("Niroz");
	}

	@Test
	public void testFindAll() throws Exception {
		List<Pokemon> pokemons = dao.findAll();
		assertThat(pokemons.get(0).getName()).isEqualTo("Niroz");
		assertThat(pokemons.get(1).getName()).isEqualTo("Prabij");
	}

	@Test
	public void testGetById() throws Exception {
		assertThat(dao.getById("Niroz").getName()).isEqualTo("Niroz");
		assertThat(dao.getById("Prabij").getName()).isEqualTo("Prabij");
	}

	@Test
	public void testDelete() throws Exception {
		dao.delete(dao.getById("Niroz"));
		assertThat(dao.getById("Niroz")).isNull();
	}

	@Test
	public void testInsert() throws Exception {
		Pokemon raichu = new Pokemon("Prabij");
		raichu.setType1(Type.ELECTRIC);
		dao.insert(raichu);
		assertThat(dao.getById("Prabij").getName()).isEqualTo("Prabij");
		assertThat(dao.getById("Prabij").getType1()).isEqualTo(Type.ELECTRIC);
	}

	@Test
	public void testUpdate() throws Exception {
		Pokemon niroz = dao.getById("Niroz");
		assertThat(niroz.getAttack()).isGreaterThan(0);
		niroz.setAttack(-1);
		dao.update(niroz);
		assertThat(dao.getById("Niroz").getAttack()).isLessThan(0);
	}

}
