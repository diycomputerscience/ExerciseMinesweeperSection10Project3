package com.diycomputerscience.minesweeper;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FilePersistenceStrategyTest {
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	private Board board;
	private String fileName = "c:/tmp/jminesweepertest.db";
	private FilePersistenceStrategy persistenceStrategy;
	private HardcodedMineInitializationStrategy mineInitializationStrategy;																									
	
	@Before
	public void setUp() throws Exception {
		this.mineInitializationStrategy = new HardcodedMineInitializationStrategy();
		this.board = new Board(mineInitializationStrategy);
		this.persistenceStrategy = new FilePersistenceStrategy(fileName);
	}

	@After
	public void tearDown() throws Exception {
		File dbFile = new File(fileName);
		if(dbFile.exists()) {
			dbFile.delete();
		}
	}

	@Test
	public void testNullPointerCheckInConstructor() throws Exception {
		expectedEx.expect(NullPointerException.class);
		expectedEx.expectMessage("fileName cannot be null");
		FilePersistenceStrategy fps = new FilePersistenceStrategy(null);
	}
	
	@Test
	public void testNullPointerCheckInSave() throws Exception {
		expectedEx.expect(NullPointerException.class);
		expectedEx.expectMessage("board cannot be null");
		FilePersistenceStrategy fps = new FilePersistenceStrategy("");
		fps.save(null);
	}
	
	@Test
	public void testSave() throws Exception {
		this.board.uncover(new Point(1, 3));
		this.persistenceStrategy.save(this.board);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(this.fileName));
			String line = null;
			int count = 0;
			while((line = reader.readLine()) != null) {
				String msg = "Line in saved file did not match on line " + count;
				assertEquals(msg, MockPersistenceStrategy.expectedLinesInSavedBoard[count], line);
				count++;
			}
		} finally {
			if(reader != null) {
				reader.close();
			}
		}
	}
	
	@Test
	public void testLoad() throws Exception {
		// create the database file
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileWriter(this.fileName));
			for(String line : MockPersistenceStrategy.expectedLinesInSavedBoard) {
				writer.println(line);
			}
		} finally {
			if(writer != null) {
				writer.close();
			}			
		}
		
		Board board = this.persistenceStrategy.load();
		Square squares[][] = board.getSquares();
		int count = 0;
		for(int row=0; row<squares.length; row++) {
			for(int col=0; col<squares[row].length; col++) {
				Square square = squares[row][col];
				String actualData = FilePersistenceStrategy.dataForSquare(row, col, square);
				String expectedData = MockPersistenceStrategy.expectedLinesInSavedBoard[count];
				String msg = "Incorrect square for row " + row + " col " + col;
				assertEquals(msg, expectedData, actualData);
				count++;
			}
		}
	}
}
