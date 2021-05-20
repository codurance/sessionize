package com.codurance.sessionize.sessionizeservice.users;

public class UsersService {

    UserService(String connectionString){
        this.mongoClient = MongoClients.create(connectionString);
        super(mongoClient);
    }
    public void create(SessionizeUser sessionizeUser) {
        throw new UnsupportedOperationException();
    }

    public SessionizeUser getByEmail(String email) {
        throw new UnsupportedOperationException();
    }
}
