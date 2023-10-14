package com.messanger.Repository;

import com.messanger.DTO.ShowMessageDTO;
import com.messanger.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query ("SELECT m FROM Message as m WHERE (m.fromUserId = :fromUserId AND m.toUserId = :toUserId) OR (m.fromUserId = :toUserId AND m.toUserId = :fromUserId)")
    List<Message> findMessageByFromUserIdAndToUserId(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);
}
