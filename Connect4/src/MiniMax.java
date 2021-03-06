
public class MiniMax 
{
	int m_nMaxPly = 7;
	
	//  This is the piece to search for. Should be either RED or YELLOW.
	int m_nSearchPiece = 0;
	Integer alpha = Integer.MIN_VALUE;
	Integer beta = Integer.MAX_VALUE;
	
	// Set the piece for which a move will be found.
	public void SetSearchPiece( int nPiece )
	{
		m_nSearchPiece = nPiece;
	}

	// Compile a list of legal moves.
	int GetLegalMoves( int[][] nBoardData, int[] nMoveList )
	{
		int row, col;
		int nNumMoves = 0;

		// Loop through six rows.
		for( row=0; row<=5; row++ )
		{
			// Loop through seven columns.
			for( col=0; col<=6; col++ )
			{
				// If the board at this location is empty,
				//   then this square is a legal move.
				if( nBoardData[row][col] == Connect4.EMPTY &&
					( row == 5 || nBoardData[row+1][col] != Connect4.EMPTY ) )
				{
					// Put the row and column in the move list array.
					nMoveList[nNumMoves*2] = row;
					nMoveList[nNumMoves*2+1] = col;
					// Increment the index pointer.
					nNumMoves++;
				}
			}
		}

		// Return the number of legal moves that were found.
		return( nNumMoves );
	}

	/*
	int DoSearch( Position pos, int nPiece, int nDepth, Board pBoard )
	{
		// Local arrays for the legal move list and
		//   the result list.
		int[] nMoveList = new int[7*2];
		
		// Create min and max variables.
		int nMin = 2000000, nMax = -2000000;

		// First, see if a side has won.
		if( pBoard.DidSideWin( nPiece ) )
		{
			if( nPiece == m_nSearchPiece )
			{
				return( 2000000 );
			}
			return( -2000000 );
		}
		
		// See if we have a Cats game.
		else if( pBoard.IsCatsGame() )
		{
			// Score for Cats game is 0.
			return( 0 );
		}
		
		// If we are at a leaf node, return the score.
		else if( nDepth >= m_nMaxPly )
		{
			return( ScoreIt( m_nSearchPiece, pBoard.GetBoardData() ) );
		}

		// Get the legal moves.
		int nMoves = GetLegalMoves( pBoard.GetBoardData(), nMoveList );

		if( nDepth == 0 )
		{
			pos.Row = nMoveList[0];
			pos.Col = nMoveList[1]; 
		}
		
		// Loop through the legal moves.
		for( int i=0; i<nMoves; i++ )
		{
			// We need a board clone so that we can place pieces
			//   without messing up previous board positions.
			Board SaveMe = pBoard.Clone();
			
			// Place the piece from the current move in the list.
			SaveMe.PlacePiece( nMoveList[i*2], nMoveList[i*2+1], nPiece );
			
			// Call DoSearch() recursively.
			int res = DoSearch( pos, nPiece ^ 1, nDepth + 1, SaveMe );

			// Check to see if this result is less than the current min.
			if( nMin > res )
			{
				// Set nMin.
				nMin = res;
			}
			
			// Check to see if this result is greater than the current max.
			if( nMax < res )
			{
				// Set nMax.
				nMax = res;
				
				if( nDepth == 0 )
				{
					pos.Row = nMoveList[i*2];
					pos.Col = nMoveList[i*2+1]; 
				}
			}
			
		}
		
		// If this is max of minimax, then return the max.
		if( nPiece == m_nSearchPiece )
		{
			return( nMax );
		}
		
		// If this is min of minimax, then return the min.
		else
		{
			return( nMin );
		}
		
	}
	 */
	
	////
	int DoSearch( Position pos, int nPiece, int nDepth, Board pBoard, int nAlpha, int nBeta )
	{
		// Local arrays for the legal move list and
		//   the result list.
		int[] nMoveList = new int[7*2];

		// First, see if a side has won.
		if( pBoard.DidSideWin( nPiece ) )
		{
			if( nPiece == m_nSearchPiece )
			{
				return( 2000000 );
			}
			return( -2000000 );
		}
		
		// See if we have a Cats game.
		else if( pBoard.IsCatsGame() )
		{
			// Score for Cats game is 0.
			return( 0 );
		}
		
		// If we are at a leaf node, return the score.
		else if( nDepth >= m_nMaxPly )
		{
			return( ScoreIt( m_nSearchPiece, pBoard.GetBoardData() ) );
		}

		// Get the legal moves.
		int nMoves = GetLegalMoves( pBoard.GetBoardData(), nMoveList );

		if( nDepth == 0 )
		{
			pos.Row = nMoveList[0];
			pos.Col = nMoveList[1]; 
		}
		//check if this is the maximizer
		if( nPiece == m_nSearchPiece )
		{
			
			// Loop through the legal moves.
			for( int i=0; i<nMoves; i++ )
			{
				// We need a board clone so that we can place pieces
				//   without messing up previous board positions.
				Board SaveMe = pBoard.Clone();
			
				// Place the piece from the current move in the list.
				SaveMe.PlacePiece( nMoveList[i*2], nMoveList[i*2+1], nPiece );
			
				int saveAlpha = nAlpha;
				// Call DoSearch() recursively.
				int retVal = DoSearch( pos, nPiece ^ 1, nDepth + 1, SaveMe, nAlpha, nBeta );
				
				nAlpha = Math.max( nAlpha, retVal );
				if( nDepth == 0 && saveAlpha < nAlpha )
				{
					pos.Row = nMoveList[i*2];
					pos.Col = nMoveList[1*2+1];
				}
				if( retVal >= nBeta )
					return retVal;
			}
			return nAlpha;
			
		}
		//for minimizer
		else
		{
			// Loop through the legal moves.
			for( int i=0; i<nMoves; i++ )
			{
				Board SaveMe = pBoard.Clone();
				// Place the piece from the current move in the list.
				SaveMe.PlacePiece( nMoveList[i*2], nMoveList[i*2+1], nPiece );
				
				int retVal = DoSearch( pos, nPiece ^ 1, nDepth + 1, SaveMe, nAlpha, nBeta );
					
				nBeta = Math.min( nBeta, retVal );
				
				if( retVal <= nAlpha )
					return retVal;	
			}
			return nBeta;
		}
		
	}
	////

	// Wrapper method that kicks off minimax to get a move.
	public void GetMove( Position pos, int[][] BoardData, int nPiece )
	{
		// Set the search piece.
		SetSearchPiece( nPiece );
		
		// Create a new board with this board data.
		Board brd = new Board();
		brd.SetBoardData( BoardData );

		// Call the recursive method.
		DoSearch( pos, nPiece, 0, brd, alpha, beta );
		//DoSearch( pos, nPiece, 0, brd );
	}
	
	int ScoreIt( int nPiece, int[][] BoardData )
	{
		int Twos = 0;
		int Threes = 0;
		int Fours =0 ;
		
		for( int nRow=0; nRow<6; nRow++ )
		{
			int nCount = 0;
			for( int nCol=0; nCol<7; nCol++ )
			{
				if( BoardData[nRow][nCol] == nPiece )
				{
					nCount++;
				}
				else
				{
					if( nCount == 2 )
					{
						Twos++;
					}
					else if( nCount == 3 )
					{
						Threes++;
					}
					else if( nCount == 4 )
					{
						Fours++;
					}
					nCount = 0;
				}
			}
			if( nCount == 2 )
			{
				Twos++;
			}
			else if( nCount == 3 )
			{
				Threes++;
			}
			else if( nCount == 4 )
			{
				Fours++;
			}
		}
		
		for( int nCol=0; nCol<7; nCol++ )
		{
			int nCount = 0;
			for( int nRow=0; nRow<6; nRow++ )
			{
				if( BoardData[nRow][nCol] == nPiece )
				{
					nCount++;
				}
				else
				{
					if( nCount == 2 )
					{
						Twos++;
					}
					else if( nCount == 3 )
					{
						Threes++;
					}
					else if( nCount == 4 )
					{
						Fours++;
					}
					nCount = 0;
				}
			}
			if( nCount == 2 )
			{
				Twos++;
			}
			else if( nCount == 3 )
			{
				Threes++;
			}
			else if( nCount == 4 )
			{
				Fours++;
			}
		}
		
		// Loop through the diagonal data.
		for( int nDiagonalTest=0; nDiagonalTest<Board.m_nDiagonalData.length/4; nDiagonalTest++)
		{
			int nCount = 0;
			// Starting row.
			int nRow = Board.m_nDiagonalData[nDiagonalTest*4];
			// Starting column.
			int nCol = Board.m_nDiagonalData[nDiagonalTest*4+1];
			// YDirection for the iterations.
			int nYDir = Board.m_nDiagonalData[nDiagonalTest*4+2];
			// Number of iterations.
			int nIterations = Board.m_nDiagonalData[nDiagonalTest*4+3];

			// Loop through the iterations.
			for( int i=0; i<nIterations; i++ )
			{
				// If this is nSide then increment the counter.
				if( BoardData[nRow][nCol] == nPiece )
				{
					// Increment the counter.
					nCount++;
				}
				
				// This square does not equal nSide.
				else
				{
					if( nCount == 2 )
					{
						Twos++;
					}
					else if( nCount == 3 )
					{
						Threes++;
					}
					else if( nCount == 4 )
					{
						Fours++;
					}
					
					// Reset the counter.
					nCount = 0;
				}
				
				// Move the row position.
				nRow += nYDir;
				// Move the column position.
				nCol++;
			}
			
			if( nCount == 2 )
			{
				Twos++;
			}
			else if( nCount == 3 )
			{
				Threes++;
			}
			else if( nCount == 4 )
			{
				Fours++;
			}
			
		}

		int nPositionalAdvantage = 0;
		for( int nCol=0; nCol<7; nCol++ )
		{
			int nCount = 0;
			for( int nRow=0; nRow<6; nRow++ )
			{
				if( BoardData[nRow][nCol] == nPiece )
				{
					nCount++;
				}
			}
			
			if( nCol == 2 || nCol == 3 )
			{
				nPositionalAdvantage += nCount * 2;
			}
			else if( nCol == 1 || nCol == 4 )
			{
				nPositionalAdvantage += nCount;
			}
			
		}
		for( int nCol=0; nCol<7; nCol++ )
		{
			int nCount = 0;
			for( int nRow=0; nRow<6; nRow++ )
			{
				if( BoardData[nRow][nCol] != nPiece )
				{
					nCount++;
				}
			}
			
			if( nCol == 2 || nCol == 3 )
			{
				//nPositionalAdvantage -= nCount * 2;
			}
			else if( nCol == 1 || nCol == 4 )
			{
				//nPositionalAdvantage -= nCount;
			}
			
		}
		
		return( nPositionalAdvantage + Twos + Threes * 2 + Fours * 4 );
	}

	static String ArrayToString( int[] IntArray )
	{
		String strRet = "{ ";
		for( int i=0; i<IntArray.length; i++ )
		{
			strRet += (""+IntArray[i] );
			if( i < IntArray.length - 1 )
			{
				strRet += ", ";
			}
		}
		return( strRet + " }");
	}
	
}

