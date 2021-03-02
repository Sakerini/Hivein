CREATE or replace FUNCTION deleteUsersWithProfiles() RETURNS trigger AS
$deleteUsersWithProfiles$
BEGIN
    delete from profiles where profiles.id = old.profile_id;
    return old;
END
$deleteUsersWithProfiles$ LANGUAGE plpgsql;

CREATE TRIGGER deleteUsersWithProfiles
    AFTER DELETE
    ON users
    for each row
EXECUTE PROCEDURE deleteUsersWithProfiles();

CREATE or replace FUNCTION deleteProfileWithAddress() RETURNS trigger AS
$deleteProfileWithAddress$
BEGIN
    delete from addresses where addresses.id = old.address_fk;
    return old;
END
$deleteProfileWithAddress$ LANGUAGE plpgsql;

CREATE TRIGGER deleteProfileWithAddress
    AFTER DELETE
    ON profiles
    for each row
EXECUTE PROCEDURE deleteProfileWithAddress();
