= Description

This project is for Subsystem testing. in particular, the impact on iPOJO (that
is one of our main building blocks).

= Content
This project is a multi-module project.
There are 2 main sub-project: hello and agent-installer.
hello is the bundle containing an iPOJO component and its instance.
agent-installer is the entry point for subsystem installation.

= Installation

== Virgo
Glyn Normington has built a Virgo instance that supports subsystems through Aries.
Clone its repository:
  git clone https://github.com/glyn/aries-subsystems-on-virgo-kernel.git

== Subsystem examples

Clone the repository.
   mvn clean install

Then simply copy the agent-installer bundle in the pickup/ directory of Virgo.

== Starting Virgo
   bin/startup.sh -clean -console -debug
