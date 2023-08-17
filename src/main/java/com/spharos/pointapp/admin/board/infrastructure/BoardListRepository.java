package com.spharos.pointapp.admin.board.infrastructure;

import com.spharos.pointapp.admin.board.domain.BoardList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardListRepository extends JpaRepository<BoardList, Long> {
}
