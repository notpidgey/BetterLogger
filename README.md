
# BetterLogger
BetterLogger is a plugin for servers running Spigot 1.15 that tracks user interactions into a database for further examination.

## Plugin features

BetterLogger currently has the following features:
- SQLite server logging
- YAML plugin configuration
	- Customize date/time format
	- Whitelist settings
	- Database size limitation
	- Database compression using 7zip
- Ingame command support
- Desktop application for querying data

## Better Data Analysis
Steps to export (via BetterLogger)
1. Type in /BetterLogger export (BlockInteractions,PlayerEvents, etc)
2. Check designated server plugin folder and open BetterLogger

Steps to export (via SQLite Studio)
1. Open SQLite
2. Click Tools and Export
3. Select Single Table
4. Choose table you're looking to export
5. Save as CSV

(https://img.pidgey.software/f/MentalWorkUnitedSquadW.gif)
With this you will easily be able to filter your data through Microsoft Excel

## Licence
Copyright © Anton H and Contributors. Licensed under the MIT Licence (MIT). See [LICENCE](LICENCE.md) in the repository root for more information.