import tkinter as tk
from tkinter import messagebox
import re

def generate_response(message):
	if "hello" in message.lower() or "hi" in message.lower():
		return "Hello! How can I assist you?"
	elif "thank you" in message.lower() or "thanks" in message.lower():
		return "You're welcome!"
	elif "How are you?" in message or "How you doing?" in message:
		return "I am doing fine! How can I assist you?"
	elif "what is your name?" in message:
		return "My name is ShayBox"
	elif message.lower() == "who created you?":
		return 'I was created by Shay for her Capstone Python Project'
	else:
		return "I'm sorry, I don't understand."

def calculate_math_operation(message):
	pattern = r"(\d+)\s*([\+\-\*\/])\s*(\d+)"
	match = re.match(pattern, message)
	if match:
		num1 = int(match.group(1))
		operator = match.group(2)
		num2 = int(match.group(3))
		if operator == "+":
			return num1 + num2
		elif operator == "-":
			return num1 - num2
		elif operator == "*":
			return num1 * num2
		elif operator == "/":
			if num2 != 0:
				return num1 / num2
			else:
				messagebox.showerror("Error", "Division by zero is not allowed.")
	return None

class ChatBox:
	def __init__(self, screen):
		self.screen = screen
		screen.title("ShayBox")

		# Create chat display area
		self.chat_display = tk.Text(screen, state=tk.DISABLED)
		self.chat_display.pack()

		# Create input box
		self.input_box = tk.Entry(screen)
		self.input_box.pack()
		self.input_box.focus()
	# Bind Enter key to send message
		self.input_box.bind("<Return>", self.send_message)

		# Display initial message
		self.display_message(" Hi! I am ShayBox the Chat Box .")
		self.display_message(" I can perform basic mathematical calculations.")
		self.display_message("\n")

	def send_message(self, event):
		message = self.input_box.get()
		self.input_box.delete(0, tk.END)
		self.display_message("You: " + message)

		# Check if it's a mathematical operation
		result = calculate_math_operation(message)
		if result is not None:
			self.display_message("ShayBox: " + str(result))
		else:
			# Generate a response
			response = generate_response(message)
			self.display_message("ShayBox: " + response)

	def display_message(self, message):
		self.chat_display.configure(state=tk.NORMAL)
		self.chat_display.insert(tk.END, message + "\n")
		self.chat_display.configure(state=tk.DISABLED)
		self.chat_display.see(tk.END)


if __name__ == "__main__":
	root = tk.Tk()
	chat_box = ChatBox(root)
	root.mainloop()
