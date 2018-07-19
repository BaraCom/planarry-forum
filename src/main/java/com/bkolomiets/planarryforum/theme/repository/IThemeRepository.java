package com.bkolomiets.planarryforum.theme.repository;

import com.bkolomiets.planarryforum.theme.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Borislav Kolomiets
 */
@Repository
public interface IThemeRepository extends JpaRepository<Theme, Long> {

    Theme findByThemeTitle(final String title);

//    void deleteByThemeTitle(final String title);
}
