package com.orders;

import org.springframework.data.repository.CrudRepository;

//public interface TicketListRepository extends CrudRepository<TicketList, TicketListId> {
//}
public interface TicketListRepository extends CrudRepository<TicketList, Long> {
}
//public interface TicketListRepository {
//}
