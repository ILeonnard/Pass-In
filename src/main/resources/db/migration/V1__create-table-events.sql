CREATE TABLE events (
    id VARCHAR(225) NOT NULL PRIMARY KEY,
    title VARCHAR(225) NOT NULL,
    details VARCHAR(225) NOT NULL,
    slug VARCHAR(225) NOT NULL,
    maximum_attendees INTEGER NOT NULL
);