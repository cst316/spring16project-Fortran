package checkers;
     
import java.util.Vector;
//game tree implementation
public class GameEngine {

    final static int inf = Integer.MAX_VALUE;
    final static int normal = 100;         //one checker worth 100
    final static int king=200;             //a King's worth
    final static int pos=1;                //one position along the -j worth 1
    final static int edge=10;               // effect of king being on the edge

/*********************  Evaluation Function  **********************************

For normal checkers;
    Eval=(weightOfChecker + PositionWeight*(position)^2)*numberOfCheckers + randomWeight;

For kings;
    Eval=(weightOfKing - EdgeWeight*numberOfEdges)*numberOfKings + randomWeight;

Weight of checkers
    Own normal checker = 100
    Opposite normal checker = -100
    Own king = 200
    Opposite king = -200
  
    For normal checkers there is a weight for position.
    The evaluted are less in edges for kings, so that they tend to be in the middle
    Small random weight between 0-10 was added to reduce the monotony of the game.
**********************************************************************************/

    public static int eval(int [][] board){
        int score=0;

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
	            if (board[i][j] == Checkers.redNormal)
                {
                      score-=normal;
                      score-=pos*j*j;
                }

                else if (board[i][j] ==Checkers.redKing){
                    score-=king;
                    if (i==0 || i==7)
                        score += edge;
                    if (j==0 || j==7)
                        score += edge;
                }

                else if (board[i][j] == Checkers.yellowNormal)
                {
                      score+=normal;
                      score+=pos*(7-j)*(7-j);
                }

                else if (board[i][j] == Checkers.yellowKing){
                    score+=king;
                    if (i==0 || i==7)
                        score -= edge;
                    if (j==0 || j==7)
                        score -= edge;
                }
            }
        }
        score += (int)(Math.random() * 10);                    //Adds a random weight

        return score;
    }

/********************  MinMax with Alpha-Beta Pruning  ************************
 * returns the best score evaluated MinMax algorithm with alpha beta pruning
 * cut off the tree according to the difficulty level
 * With maximum difficulty it searches upto 6 levels for the best possible solution
 * With minimum difficulty it searches only upto two levels.
****************************************************************************/

      public static int MinMax(int[][] board, int depth, int maxDepth, int[] move, int toMove, int[] counter)
      {
          return MinMax(board,depth,maxDepth,move,toMove,counter,inf,-inf);
      }

      static int MinMax(int[][] board, int depth, int maxDepth,int[] move, int turn, int[] counter, int redBest, int yellowBest)
      {
        int score,bestScore;
        int[][] newBoard;
        int[] bestMove=new int[4];

        Vector movesList;  //vector of 4x1 arrays

        //assumes that depth is never equal to maxDepth to begin with since chosenMove is not set here

        if (depth==maxDepth)
        {
            bestScore = eval(board);
            counter[0]++;
        }

        else {
            movesList = CheckerMove.generateMoves(board,turn);
            bestScore=getTurn(turn);
            switch (movesList.size())
            {
            case 0:
                counter[0]++;
                return bestScore;
            case 1:
              if (depth == 0)
              {
                  // forced move: immediately return control
                bestMove = (int[])movesList.elementAt(0);
                  System.arraycopy(bestMove, 0, move, 0, 4);
                return 0;
              }
              else
              {
                  // extend search since there is a forcing move
                  maxDepth += 1;
              }
            }

            for (int i=0;i<movesList.size();i++){
                newBoard = copyBoard(board);                                     //board need not be touched
                CheckerMove.moveComputer(newBoard, (int[])movesList.elementAt(i)); /*returns new_board (changing start and end coodinates
                                                                                     and applying the move)*/
                int temp[] = new int[4];
                score= MinMax(newBoard, depth+1, maxDepth, temp, getOpponent(turn), counter, yellowBest, redBest);

                if (turn==Checkers.yellowNormal && score > bestScore) {
                    bestMove = (int[])movesList.elementAt(i);
                    bestScore = score;
                    if (bestScore > yellowBest)
                    {
                        if (bestScore >= redBest)
                            break;  /*  alpha_beta cutoff  */
                        else
                            yellowBest = bestScore;  //the_score
                    }
                }

                else if (turn==Checkers.redNormal && score < bestScore) {
                    bestMove = (int[])movesList.elementAt(i);
                    bestScore = score;
                    if (bestScore < redBest)
                    {
                        if (bestScore <= yellowBest)
                            break;  /*  alpha_beta cutoff  */
                        else
                            redBest = bestScore;  //the_score
                    }
                }
            }
        }

          System.arraycopy(bestMove, 0, move, 0, 4);
        return bestScore;
    }

      static int[][] copyBoard(int[][] board){       //uses to copy a double array
        int[][] copy = new int[8][8];

        for (int i=0; i<8; i++)
          System.arraycopy(board[i],0,copy[i],0,8);
        return copy;
      }
    
    static int getOpponent(int turn){                  //returns the opponent
         return turn==Checkers.yellowNormal ? Checkers.redNormal : Checkers.yellowNormal;
    }

    static int getTurn(int turn) {                     //returns the turn
          return CheckerMove.colour(turn)==Checkers.yellowNormal ? -inf : inf;
    }
}