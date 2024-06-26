# mysql -u root -p -h localhost -P 3306
# member
CREATE DATABASE `train_member` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';

CREATE USER `train_member`@`%` IDENTIFIED BY 'member.102';

GRANT Alter, Alter Routine, Create, Create Routine, Create Temporary Tables, Create View, Delete, Drop, Event, Execute, Grant Option, Index, Insert, Lock Tables, References, Select, Show View, Trigger, Update ON `train_member`.* TO `train_member`@`%`;

# business
CREATE DATABASE `train_business` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';

CREATE USER `train_business`@`%` IDENTIFIED BY 'business.102';

GRANT Alter, Alter Routine, Create, Create Routine, Create Temporary Tables, Create View, Delete, Drop, Event, Execute, Grant Option, Index, Insert, Lock Tables, References, Select, Show View, Trigger, Update ON `train_business`.* TO `train_business`@`%`;

