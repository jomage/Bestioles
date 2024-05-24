-- INSERT Species
INSERT INTO species (common_name, latin_name) VALUES('Chat', 'Felis silvestris catus');
INSERT INTO species (common_name, latin_name) VALUES('Loup', 'Canis lupus');
INSERT INTO species (common_name, latin_name) VALUES('Lapin', 'Oryctolagus cuniculus');
INSERT INTO species (common_name, latin_name) VALUES('Cochon nain', 'Sus Scrofa Domesticus');
INSERT INTO species (common_name, latin_name) VALUES('Ornithorynque', 'Ornithorhynchus anatinus');
INSERT INTO species (common_name, latin_name) VALUES('Nouvelle espèce de chien', 'Canis Lupus Novella');
INSERT INTO species (common_name, latin_name) VALUES('Chauve-souris', 'Chiroptera');
INSERT INTO species (common_name, latin_name) VALUES('Escargot', 'Stylommatophora');
INSERT INTO species (common_name, latin_name) VALUES('Iguane', 'Iguanidae');
INSERT INTO species (common_name, latin_name) VALUES('Gecko', 'Gekko gecko');

-- INSERT Animals
INSERT INTO animal (color, name, sex, species_id) VALUES('Gris tacheté', 'Max', 'M', 1); --1
INSERT INTO animal (color, name, sex, species_id) VALUES('Blanc', 'Médor', 'M', 2); --2
INSERT INTO animal (color, name, sex, species_id) VALUES('Noir', 'Loulou', 'F', 1); --3
INSERT INTO animal (color, name, sex, species_id) VALUES('Brun', 'Zia', 'F', 2); --4
INSERT INTO animal (color, name, sex, species_id) VALUES('Blanc', 'Lou', 'F', 3); --5
INSERT INTO animal (color, name, sex, species_id) VALUES('Roux', 'Minouchette', 'M', 1); --6
INSERT INTO animal (color, name, sex, species_id) VALUES('Brun', 'Babouche', 'M', 2); --7
INSERT INTO animal (color, name, sex, species_id) VALUES('Brun foncé', 'Batbat', 'F', 7); --8
INSERT INTO animal (color, name, sex, species_id) VALUES('Gris tacheté', 'Minipig', 'F', 4); --9
INSERT INTO animal (color, name, sex, species_id) VALUES('Beige', 'Baveux', 'M', 8); --10
INSERT INTO animal (color, name, sex, species_id) VALUES('Gris et vert mais surtout vert', 'Philippe', 'M', 9); --11

-- INSERT Persons
INSERT INTO person (age, first_name, last_name) VALUES(55, 'Jean', 'Vintroi'); --1
INSERT INTO person (age, first_name, last_name) VALUES(45, 'Sophie', 'Nero'); --2
INSERT INTO person (age, first_name, last_name) VALUES(33, 'John', 'Mangolo'); --3
INSERT INTO person (age, first_name, last_name) VALUES(26, 'Bill', 'Wagner'); --4
INSERT INTO person (age, first_name, last_name) VALUES(3, 'Kilian', 'Gardavot'); --5
INSERT INTO person (age, first_name, last_name) VALUES(13, 'Mattéo', 'Hardy'); --6
INSERT INTO person (age, first_name, last_name) VALUES(4, 'Kelyan', 'Andriamisy'); --7
INSERT INTO person (age, first_name, last_name) VALUES(75, 'Geneviève', 'Defontenay'); --8
INSERT INTO person (age, first_name, last_name) VALUES(55, 'Lucie', 'Lacroix'); --9
INSERT INTO person (age, first_name, last_name) VALUES(62, 'Alicia', 'Schmitt'); --10
INSERT INTO person (age, first_name, last_name) VALUES(44, 'Océane', 'Fernandez'); --11
INSERT INTO person (age, first_name, last_name) VALUES(71, 'Lilou', 'Rivière'); --12
INSERT INTO person (age, first_name, last_name) VALUES(73, 'Alexis', 'Dubois');--13
INSERT INTO person (age, first_name, last_name) VALUES(21, 'Lenae', 'Colin-Mayard'); --14

-- INSERT Person_animals
INSERT INTO person_animals (person_id, animals_id) VALUES(1, 1);
INSERT INTO person_animals (person_id, animals_id) VALUES(1, 2);
INSERT INTO person_animals (person_id, animals_id) VALUES(2, 1);
INSERT INTO person_animals (person_id, animals_id) VALUES(2, 2);
INSERT INTO person_animals (person_id, animals_id) VALUES(3, 3);
INSERT INTO person_animals (person_id, animals_id) VALUES(4, 3);
INSERT INTO person_animals (person_id, animals_id) VALUES(5, 4);
INSERT INTO person_animals (person_id, animals_id) VALUES(5, 5);
INSERT INTO person_animals (person_id, animals_id) VALUES(5, 6);
INSERT INTO person_animals (person_id, animals_id) VALUES(6, 7);
INSERT INTO person_animals (person_id, animals_id) VALUES(7, 8);
INSERT INTO person_animals (person_id, animals_id) VALUES(8, 9);
INSERT INTO person_animals (person_id, animals_id) VALUES(9, 10);
INSERT INTO person_animals (person_id, animals_id) VALUES(11, 11);

-- INSERT Roles
INSERT INTO authority VALUES ('ROLE_ADMIN');
INSERT INTO authority VALUES ('ROLE_USER');

-- INSERT Users
-- login=admin mdp=admin
INSERT INTO user_(login, password_hash)
VALUES ('admin', '$2a$10$sBACfFJc3jK6JIYy9sYVYeVVQo0wHn0l.PUpdfAL36A9GNl1mD1Aq');
-- Ajout roles sur admin
INSERT INTO user_authority
VALUES (1, 'ROLE_ADMIN');
INSERT INTO user_authority
VALUES (1, 'ROLE_USER');

-- login=user mdp=user
INSERT INTO user_(login, password_hash)
VALUES ('user', '$2a$10$kugIaMlSF1PEWI22OVSF9etnsGpGS83NoLwVjkPz5VzFCmjD5l66u');
-- Ajout role sur user
INSERT INTO user_authority
VALUES (2, 'ROLE_USER');
