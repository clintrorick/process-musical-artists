1. Assume none of the artist names contain commas, newlines, or malicious constructs
2. Care more about readability (variable names, formatting, complexity, etc.) good practices, 
   and correctness than performance (within reason)
3. Unit tests are highly encouraged
4. Comments aren't required but should indicate intent

The attached text file contains the favorite musical artists of 1000 users from Some Popular Music Review Website.
Each line is a list of up to 50 comma-separated artist names.  

Write a program that, using this file as input, produces an output file containing a list of pairs of
artists which appear TOGETHER in at least fifty different lists.  It should be able to run from the command line.
THe solution should be a CSV, with each row being a pair.  For example:
Morrissey,Radiohead

The solution may return a best guess, i.e. pairs which appear at least 50 times with high probability, as long as you explain 
how your approach affects accuracy and why this tradeoff improves performance of the algorithm.  
Please include, either in comments or in a separate file, a brief one-paragraph description of any optimizations you made and how they 
impact the run-time of the algorithm.

Please provide written instructions for how to build, compile, and run your code.  No Jupyter notebooks.