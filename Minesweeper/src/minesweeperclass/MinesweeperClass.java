/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeperclass;

import java.util.Random;

/**
 *
 * @author l154011
 */
public class MinesweeperClass {
  int width = 9;
  int height = 9;
  int mines = 10;
  char[][] minefield;

  public MinesweeperClass() {
    minefield = new char[height][width];
    clearMinefield();
    placeMines();
    calculateHints();
  }

  public void placeMines() {
    int minesPlaced = 0;
    Random random = new Random();
    while(minesPlaced < mines) {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
      // make sure we don't place a mine on top of another
      if(minefield[y][x] != '*') {
        minefield[y][x] = '*';
        minesPlaced ++;
      }
    }
  }

  public void clearMinefield() {
    for(int y = 0; y < height; y ++) {
      for(int x = 0; x < width; x ++) {
        minefield[y][x] = ' ';
      }
    }
  }

  public void calculateHints() {
    for(int y = 0; y < height; y ++) {
      for(int x = 0; x < width; x ++) {
        if(minefield[y][x] != '*') {
          minefield[y][x] = minesNear(y, x);
        }
      }
    }
  }
  
  public char minesNear(int y, int x) {
    int mines = 0;
    mines += mineAt(y - 1, x - 1);  // North-West
    mines += mineAt(y - 1, x);      // North
    mines += mineAt(y - 1, x + 1);  // North-East
    mines += mineAt(y, x - 1);      // West
    mines += mineAt(y, x + 1);      // East
    mines += mineAt(y + 1, x - 1);  // South-West
    mines += mineAt(y + 1, x);      // South
    mines += mineAt(y + 1, x + 1);  // South-East
    if(mines > 0) {
      return (char)(mines + 48);
    } else {
      return ' ';
    }
  }

  public int mineAt(int y, int x) {
    if(y >= 0 && y < height && x >= 0 && x < width && minefield[y][x] == '*') {
      return 1;
    } else {
      return 0;
    }
  }

}
