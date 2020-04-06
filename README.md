
# BetterLogger
BetterLogger is a plugin for servers running Spigot 1.15 that tracks user interactions into a database for further examination.

## Plugin features

BetterLogger currently has the following features:
- PostgreSQL server logging

- YAML plugin configuration
	- Customize date/time format
	- Whitelist settings
	- Database size limitation
	- Database compression using 7zip


- Ingame command support

- Desktop application for querying data

## Setup
Install PostgreSQL on machine hosting Minecraft server instance

1. Setup PostgreSQL 11 (or latest stable) on your server.
    * Ubuntu 18.04 / Ubuntu 16.04
        - Update Ubuntu

        ```sudo apt update && sudo apt -y upgrade```

        - Add PostgresSQL Repository

        ```wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add```

        ```sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt/ $(lsb_release -sc)-pgdg main" > /etc/apt/sources.list.d/PostgreSQL.list'```

        - Install PostgreSQL

        ```sudo apt-get install postgresql-11```

    * Windows 10 (local setup)
      - Go to https://www.postgresql.org/download/windows/ and install preferred PostgreSQL version

      - Complete setup with installation of pgAdmin.

2. Prepare PostgreSQL for BetterLogger    
    * Ubuntu
      - Create admin user for BetterLogger
    
        ```sudo -u postgres psql```
        ```sql 
        CREATE ROLE admin WITH
          LOGIN
          SUPERUSER
          CREATEDB
          CREATEROLE
          INHERIT
          NOREPLICATION
          CONNECTION LIMIT -1
          PASSWORD 'xxxxxx'; 
        ```
    * pgAdmin (local)
      -  Right click on Login/Group Roles and set the following parameters:
        - Name: admin
        - Password: Password in configuration
        - Can Login: True
        - Superuser: True
        - Can initiate streaming replication and backups: True
        - Save

    * Launch BetterLogger

## Better Data Analysis

To further analyze server data, you may connect to the PostgreSQL database using DataGrip and other PostgreSQL tools.

1. Connect to PostgreSQL database.

    * Configure PostgreSQL

      - Edit postgresql.conf, change

        ```#listen_addresses = 'localhost' to listen_addresses = '*'```
        
      - Restart PostgreSQL service

        ```sudo service postgresql restart```

    * DataGrip
    
      - Launch DataGrip

      - Create add a new Datasource

      - Select PostgreSQL

      - Set the following options
        - Host: localhost/server ip
        - Port: 5432
        - User: admin
        - Password: xxxxxx
        - Database: Better Logger

    * pgAdmin

      - Right click servers
      
      - Create server
      
      - Set the following:
        - Name: Any Name
        - Host name/address: Server IP
        - Port: 5432
        - Maintenance Database: BetterLogger
        - Username: admin
        - Password: xxxxxx


## Licence
Copyright © Anton H and Contributors. Licensed under the MIT Licence (MIT). See [LICENCE](LICENCE.md) in the repository root for more information.
