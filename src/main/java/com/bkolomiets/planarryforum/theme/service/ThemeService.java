package com.bkolomiets.planarryforum.theme.service;

import com.bkolomiets.planarryforum.core.service.HomeService;
import com.bkolomiets.planarryforum.theme.domain.Theme;
import com.bkolomiets.planarryforum.theme.repository.IThemeRepository;
import com.bkolomiets.planarryforum.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Borislav Kolomiets
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ThemeService {
    private final IThemeRepository themeRepository;
    private final IUserRepository userRepository;
    private final HomeService homeService;

    public List<Theme> getAll() {
        return themeRepository.findAll();
    }

    public Theme getByTitle(final String title) {
        return themeRepository.findByThemeTitle(title);
    }

    public Long getIdByTitle(final String title) {
        Theme theme = themeRepository.findByThemeTitle(title);

        return theme.getId();
    }

    public void addTheme(final String title, final String description) {
        String userName = homeService.getCurrentUserName();

        Theme theme = new Theme(title, description, LocalDate.now().toString());
        theme.setUser(userRepository.findByUsername(userName));

        themeRepository.save(theme);
    }
}
