# Sudoku
Built a Sudoku Solver using Java that can solve nxn

# UPDATE
Built a simple Interface using Java's Swing GUI widget toolkit and Abstract Window Toolkit (awt)

# Algorithm
- Begin with unsolved board valid or not
- checks each open space with 0 in its position
- try placing 1 - 9 in that open position
- if we can set a number there and it is a valid position then recurse
- if not then backtrack by resetting the current position and going back 1
- if no "0" on board then algorithm finishes and board is solved!

# Learning Experience

- Backtracking Algorithm
  - recursively trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point of time. 
- Java's GUI interface


![](https://github.com/Dennayz/Sudoku/blob/master/grid.png)
