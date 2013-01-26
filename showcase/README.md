# Subsystems Showcase

## Description

This project is for Subsystem testing. It demonstrates how to build ESA modules (aka Subsystems).
It only uses native OSGi APIs (no Service Component Model like iPOJO, DS, ...).

## Content

## Build

As usual, build is performed by maven:

    >$ mvn clean install

## Install

The peergreen kernel must be started with support for Aries Subsystems. Subsystems related commands
also have to be available on the kernel.

Install Subsystem Commands Maven (if not already provided):

    >$ install mvn:com.peergreen.shelbie/subsystem-commands/1.0.0-SNAPSHOT

All Subsystems related commands are in the `subsystem` scope.

    subsystem:install-subsystem <url-of-the-subsystem-to-install> [<parent-subsystem-identifier>]
    subsystem:start-subsystem <subsystem-identifier>

Do not forget to hit [tab] key to benefit of auto-completion

### Test Subsystems

    >$ install mvn:com.peergreen.subsystems.showcase/hello-api/0.0.1-SNAPSHOT
    >$ subsystem:install-subsystem mvn:com.peergreen.subsystems.showcase/english-tenant-esa/0.0.1-SNAPSHOT/esa
    >$ subsystem:install-subsystem mvn:com.peergreen.subsystems.showcase/french-tenant-esa/0.0.1-SNAPSHOT/esa
    >$ subsystem:start-subsystem 1
    >$ subsystem:start-subsystem 2

## Bugs

* Notive that explicit bundle resource do not support bundle duplication. Even if the 2 bundles are in different regions.

