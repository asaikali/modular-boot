CREATE TABLE outbox (
   sent         BOOLEAN DEFAULT FALSE NOT NULL,
   to_address   VARCHAR(320) NOT NULL,
   from_address VARCHAR(320) NOT NULL,
   subject      TEXT NOT NULL,
   body         TEXT NOT NULL
);
