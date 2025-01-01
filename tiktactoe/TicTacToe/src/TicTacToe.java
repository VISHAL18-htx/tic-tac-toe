import java.awt.*;
import java.awt.event.*;
import javax.swing.*;





public class TicTacToe {
    int boardWidth ;
    int boardHeight ;

    JFrame frame;  //window create with name at border
    JButton restratJLabel; //
    
    JLabel textLabel  ;
    JPanel textPanel  ;
    JPanel boardPanel ;

    JButton[][] board ; // array is created for button on board
    String playerX ;
    String playerO ;
    String currentPlayer  ;// first turn will be of X in Game

    boolean GameOver = false;
    int turns;

    TicTacToe(){
        init();

    }
    void init(){
         boardWidth = 600;
         boardHeight =600;

     frame=new JFrame("Tic-Tac-Toe");  //window create with name at border
     restratJLabel= new JButton("restart"); //
    
     textLabel  = new JLabel();
     textPanel  = new JPanel();
     boardPanel = new JPanel();

     board = new JButton[3][3]; // array is created for button on board
     playerX = "X";
     playerO = "O";
     currentPlayer = playerX;// first turn will be of X in Game

     GameOver = false;
     turns= 0;


        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null); //used for screen to be in centre
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x= close opertation
        frame.setLayout(new BorderLayout());

        restratJLabel.setBackground(Color.darkGray);////
        restratJLabel.setForeground(Color.yellow);

        restratJLabel.setFont(new Font("Arial", Font.BOLD,50));
        restratJLabel.setHorizontalAlignment(JLabel.CENTER);
        restratJLabel.setFocusable(false);///
        

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD,50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(restratJLabel, BorderLayout.SOUTH);////
        restratJLabel.addActionListener(new ActionListener() {//////
            public void actionPerformed(ActionEvent e){
                
                JButton tile=(JButton) e.getSource();

                if(tile.getText()== "restart"){
                     
                    init();
                    

                  
                }
                
            }
            
        }); ////
        
        frame.add(textPanel, BorderLayout.NORTH); // tic tac toe at center up in black

        // creating board
        boardPanel.setLayout(new GridLayout(3,3));// 3x3 grid layout
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel); // adding BOARD panel to the frame
         
        for (int r=0; r<3; r++){
            for(int c=0;c<3; c++){
                JButton tile = new JButton();
                board[r][c]=tile;
                boardPanel.add(tile);

                //style the buttons
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial",Font.BOLD,120));
                tile.setFocusable(false);
               // tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if (GameOver) return;
                        JButton tile=(JButton) e.getSource();

                        if(tile.getText()== ""){
                             tile.setText(currentPlayer);
                             turns++;
                             checkwinner();

                             if(!GameOver){
                                 currentPlayer = currentPlayer== playerX ? playerO: playerX; // alternate turn of palyer
                                 textLabel.setText(currentPlayer+ "'s turn.");
                             }

                          
                        }
                        
                    }
                    
                });


            }
        }
    }



    void checkwinner(){
         //horizontal
         for (int r=0;r<3;r++){
            if(board[r][0].getText() == "") continue;

            if(board[r][0].getText()== board[r][1].getText()&&
               board[r][1].getText()== board[r][2].getText()){
                for(int i=0;i<3; i++){
                    setwinner(board[r][i]);

                }
                GameOver= true;  // for horizontal after this no button will be pressed
                return;
               }
         } 

         //VERTICAl
         for(int c= 0;c<3;c++){
            if(board[0][c].getText() == "") continue;
            
            if(board[0][c].getText()== board[1][c].getText()&&
               board[1][c].getText()== board[2][c].getText()) {
                for(int i= 0; i<3; i++){
                    setwinner(board[i][c]);
                }
                GameOver =true;
                return;
               }
         }

         //diagnally
         if(board[0][0].getText()== board[1][1].getText() &&
            board[1][1].getText()== board[2][2].getText() &&
            board[0][0].getText() != "") {
            for(int i=0;i<3;i++){
                setwinner(board[i][i]);
            }
            GameOver =true;
            return;
            } 

        //antidiagonally
        if(board[0][2].getText()== board[1][1].getText() &&
          board[1][1].getText()== board[2][0].getText() &&
          board[0][2].getText() != ""){
          setwinner(board[0][2]); 
          setwinner(board[1][1]); 
          setwinner(board[2][0]);  
          GameOver=true;
          return;
 
        }

        if(turns==9){
            for(int r=0;r<3;r++){
                for(int c=0; c<3;c++)
                setTie(board[r][c]); 
            }
            GameOver=true;
            return;
        }
    }

    void setwinner(JButton tile) {
        tile.setForeground(Color.blue);
        tile.setBackground(Color.white);
        textLabel.setText(currentPlayer + " is the WINNER");

    }

    void setTie(JButton tile){
        tile.setForeground(Color.red);
        tile.setBackground(Color.gray);
        textLabel.setText("tie:~");
    }
}
