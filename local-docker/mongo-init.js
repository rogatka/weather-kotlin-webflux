db.createUser(
    {
      user: "weather",
      pwd: "weather",
      roles: [
        {
          role: "readWrite",
          db: "cities_db"
        }
      ]
    }
);

db.createUser(
    {
        user: "liquibase_user",
        pwd: "liquibase_pass",
        roles: [
            {
                role: "readWrite",
                db: "cities_db"
            },
            {
                role: "dbAdmin",
                db: "cities_db"
            }
        ]
    }
);