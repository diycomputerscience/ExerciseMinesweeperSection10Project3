package com.diycomputerscience.minesweeper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class FilePersistenceStrategy implements PersistenceStrategy {

	private String fileName;
	// Format for saving a square 'row,col:SqaureStatus-isMine'
	public static final String SQUARE_FORMAT = ""; //TODO: Give a format to SQUARE_FORMAT
	public static final String SQUARE_LOAD_REGEX = ""; //TODO Give a regex to SQUARE_LOAD_REGEX
	
	private Logger logger = Logger.getLogger(FilePersistenceStrategy.class);
	
	public FilePersistenceStrategy(String fileName) {
		if(fileName == null) {
			throw new NullPointerException("fileName cannot be null");
		}
		this.fileName = fileName;
	}
	
	@Override
	public void save(Board board) throws PersistenceException {
		if(board == null) {
			throw new NullPointerException("board cannot be null");
		}
		logger.info("Saving board to file '" + this.fileName + "'");
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(this.fileName);
			Square squares[][] = board.getSquares();
			for(int row=0; row<squares.length; row++) {
				Square squareRow[] = squares[row];
				for(int col=0; col<squareRow.length; col++) {
					Square square = squareRow[col];
					String data = dataForSquare(row, col, square);
					printWriter.println(data);
				}
			}
		} catch(IOException ioe) {
			//Notice that we are not logging here
			throw new PersistenceException("Could not save data", ioe);
		} finally {
			if(printWriter != null) {
				printWriter.close();
			}
		}
	}

	@Override
	public Board load() throws PersistenceException {
		return null;		
	}
	
	public static String dataForSquare(int row, int col, Square square) {
		return null;
	}

}
