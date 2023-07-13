const jokeElement = document.getElementById('joke');
const nextButton = document.getElementById('nextButton');

// Define an array of funny jokes
const jokes = [
  "Why don't scientists trust atoms? Because they make up everything!",
  "I used to be a baker, but I couldn't make enough dough.",
  "Why don't skeletons fight each other? They don't have the guts!",
  "Why did the scarecrow win an award? Because he was outstanding in his field!",
  "Did you hear about the mathematician who's afraid of negative numbers? He will stop at nothing to avoid them!"
];

let currentJokeIndex = 0;

// Display the initial joke
jokeElement.textContent = jokes[currentJokeIndex];

// Add event listener to the nextButton
nextButton.addEventListener('click', () => {
  // Increment the current joke index
  currentJokeIndex = (currentJokeIndex + 1) % jokes.length;
  // Display the next joke
  jokeElement.textContent = jokes[currentJokeIndex];
});
