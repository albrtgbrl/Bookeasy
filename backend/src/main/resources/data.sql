INSERT INTO sede (nome, indirizzo) VALUES ('Sede Centrale Milano', 'Via Dante 10');
INSERT INTO sede (nome, indirizzo) VALUES ('Succursale Roma', 'Via Nazionale 55');

INSERT INTO sala (nome, capienza, id_sede) VALUES ('Sala Alpha', 10, 1);
INSERT INTO sala (nome, capienza, id_sede) VALUES ('Sala Beta', 18, 1);
INSERT INTO sala (nome, capienza, id_sede) VALUES ('Sala Gamma', 6, 2);

INSERT INTO utente (nome, cognome, email) VALUES ('Mario', 'Rossi', 'mario.rossi@bookeasy.com');
INSERT INTO utente (nome, cognome, email) VALUES ('Luca', 'Bianchi', 'luca.bianchi@bookeasy.com');
INSERT INTO utente (nome, cognome, email) VALUES ('Elena', 'Verdi', 'elena.verdi@bookeasy.com');

INSERT INTO prenotazione (id_sala, id_utente, data_prenotazione, ora_inizio, ora_fine) 
VALUES (1, 1, '2026-06-01', '09:00:00', '11:00:00');

INSERT INTO prenotazione (id_sala, id_utente, data_prenotazione, ora_inizio, ora_fine) 
VALUES (1, 2, '2026-06-01', '14:00:00', '16:00:00');

INSERT INTO prenotazione (id_sala, id_utente, data_prenotazione, ora_inizio, ora_fine) 
VALUES (2, 3, '2026-06-01', '10:00:00', '12:00:00');

INSERT INTO prenotazione (id_sala, id_utente, data_prenotazione, ora_inizio, ora_fine) 
VALUES (3, 1, '2026-06-02', '11:00:00', '13:00:00');