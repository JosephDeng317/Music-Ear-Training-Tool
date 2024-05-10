# Musical Ear Training Tool
To run the application, simply run Main in the ui package. For the best experience, turn on your volume.

## About

For my term project, I will be creating an application to help people 
practice musical ear training skills. It will probably focus mostly on **intervals 
and chord quality**, and the player will be asked to identify the type of interval/chord
after hearing it. I plan to add **multiple modes**, one where the player can choose their
answer from a list, and one where the user can play their answer on an on-screen 
keyboard.

The application is intended to be used by **musicians who want to practice their 
ear-training skills**, whether it be for an exam or just for personal fun. 

I am interested in this project because **I am quite passionate about music**, and I find 
playing by ear to be one of the most fun and freeing experiences in the art-form. I 
have been playing piano for about **15 years** and teaching for almost a year now, and I 
think it would be cool to make an app that could have a real-life use, and maybe
something I can even share to my students!

## User Stories

A *bulleted* list:
- As an user, I want to be able to add the results of one test to a history of tests that store
my inputted answer and the correct answer
- As an user, I want to be able to view a list of past tests, and see which tests I got right
- As an user, I want to be able to re-listen to the test by pressing a button
- As an user, I want to be able to select the difficulty of the tests
- As an user, I want to be able to see statistics on what areas of ear training I am strong and weak in
- As an user, I want to be able to save a history of tests to a file (If I choose to)
- As an user, I want to be able to load a history of tests from a file (If I choose to)

An example of text with **bold** and *italic* fonts.

## Instructions for Grader
- For the best experience, have your volume on for this musical quiz 
- To add test results to a test history, simply play the quiz and every question you do will be automatically added
- To view the history, X out of the quiz window, and press view results on the main menu
- There is a button to hide/show each of the 3 difficulties in the result viewer panel
- There are 2 visual components designed to maximize the attention span of the user, one is in the main menu, one appears when a quiz is started
- To save the current test history, simply click save results in the main menu
- To load a saved history, click the load results button

## Phase 4: Task 2
  - Difficulty was selected as EASY
  - Quiz question answered.
  - Added singular test result to the history of the quiz instance
  - Quiz question answered.
  - Added singular test result to the history of the quiz instance
  - Saved Result of a quiz to JSON file
  - Viewing Past Result
  - Viewing Past Result
  - EASY Collection is now hidden
  - EASY Collection is now visible

## Phase 4: Task 3
If I were to refactor my code, I think that I would start off by splitting my Quiz
class into a few shorter classes. Right now, my Quiz class is quite long, and I think
it would improve the readability if I were to have other classes take care of different
responsibilities. For example, I think that adding an abstract class for Question, and
then 3 subclasses for each difficulty, and then putting an array of questions in 
the quiz class as a field would be a good idea. I feel that this is more organized,
as I can also assign a TestResult field to each question instead of assigning a 
TestResultCollection field to the whole quiz. 

I also think that I could have used interfaces and abstract classes in my design instead of
keeping them all as regular classes. Initially I wrote all my classes without abstract
or interfaces because it involves less code, but I think that using more subclasses
would have greatly improved the structure of my code (see the example on Question
class above).