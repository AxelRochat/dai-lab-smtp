# Java Prank Email Tool
-----------
## Overview

This project is a Java-based prank email tool. It generates prank email campaigns by dividing participants into groups and sending prank messages to each group via an SMTP server.
The tool does not send real pranks. It can be tested with a mock SMTP server to ensure safe experimentation. This projet is a labo for the HEIG, he has an educational goal.

-----------
## Features

- Automatically generate groups for prank campaigns
- Randomize email messages from a predefined list
- Send emails through any SMTP server (mock or real)
- Highly configurable and easy to set up
- Easy to set and modify the list of messages and emails
-----------
## Setting Up the Mock SMTP Server

To experiment without sending real emails, you can use a mock SMTP server. We recommend you to use the docker container : maildev.

Use this command to launch the docker container 
   docker run -d -p 1080:1080 -p 1025:1025 maildev/maildev

You can try to connect to this mailbox on your browser http:/localhost:1080
-----------
## Configuring and running a prank email campaign

Begin with cloning the project. For this open a command prompt in the folder where you want to copy the projet. And type this command :
    git clone <urlGithubReposite>

Then, open your favorite IDE and open the project. 
Go to the root folder of this project, where the pom.xml is, and use this command 
    mvn clean package
This command compiles the code and generates a .jar file

After that, move into the folder nammed target. You can use this command.
    cd target

When you are in this folder, there is the .jar file. We want to execute it with this command :
    java -jar <NameOfTheFile>.jar     (in my case it was java -jar .\smtp-1.0-SNAPSHOT.jar)

When this command is done, you'll see in your terminal that he has send a random email of the list to a random generated group.
To check that everything is working, open your browser and go to http:/localhost:1080
It should open a page where you can read the email that you've just send.
-----------
## Implementation 

1. ConfigurationLoader
Responsibilities:
    Reads configuration files (victims.json and email.json) to load the list of participants and email messages.
Key Methods:
    loadVictimsFromJson(): Returns a list of email addresses read from victims.json.
    loadMessagesFromJson(): Returns a list of EmailMessage objects created from email.json.
Details:
    It uses the ObjectMapper class from the Jackson library for JSON parsing.
Example: If victims.json contains:
{ "victims": ["alice@example.com", "bob@example.com"] }
The loadVictimsFromJson method returns: ["alice@example.com", "bob@example.com"].

2. PrankGenerator
Responsibilities:
    Divides participants into groups and assigns prank messages.
Key Methods:
    createGroups(): Randomly creates prank groups with a sender and recipients.
    getRandomMessage(): Selects a random prank email message from the list.
Details:
    Ensures that each group contains between 2 and 5 members.
Example: For a group
Group 1: ["alice@example.com", bob@example.com", "charlie@example.com"]
Sender: alice@example.com, Recipients: ["bob@example.com", "charlie@example.com"]

3. EmailClient
Responsibilities:
    Manages the communication with the SMTP server to send prank emails.
Key Methods:
    connect(): Establishes a connection to the SMTP server.
    sendEmail(String sender, List<String> recipients, EmailMessage message): Sends an email from a sender to a list of recipients.
    disconnect(): Closes the SMTP connection.
Details:
    Sends SMTP commands such as MAIL FROM, RCPT TO, and DATA.

4. EmailMessage
Responsibilities:
    Represents an email message with a subject and body.
Key Methods:
    getSubject(): Returns the subject of the email.
    getBody(): Returns the body of the email.
Example: For an email with:
{ "subject": "Prank!", "body": "This is a prank message." }
The EmailMessage object contains:
    Subject: Prank!
    Body: This is a prank message.

5. MainApp
    Responsibilities:
    Orchestrates the entire prank email campaign by:
    Loading configurations.
    Generating groups and messages.
    Sending emails through the SMTP server.
Details:
    Combines all other components to execute the prank campaign.
    Ensures proper connection and disconnection from the SMTP server.
Example Workflow:
    Loads participants: ["alice@example.com", "bob@example.com"].
    Creates groups:
    Group 1: Sender: alice@example.com, Recipients: ["bob@example.com"].
    Sends emails using EmailClient