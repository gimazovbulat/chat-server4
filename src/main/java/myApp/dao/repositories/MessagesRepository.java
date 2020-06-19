package myApp.dao.repositories;

import myApp.models.Message;

import java.util.List;

public interface MessagesRepository extends CrudRepository<Long, Message> {
     List<Message> getMessages(int size, int page);
}
