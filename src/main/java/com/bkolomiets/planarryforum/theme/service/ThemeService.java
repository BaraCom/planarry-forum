package com.bkolomiets.planarryforum.theme.service;

import com.bkolomiets.planarryforum.theme.domain.Theme;
import com.bkolomiets.planarryforum.theme.repository.IThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Borislav Kolomiets
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ThemeService {
    private final IThemeRepository themeRepository;

    public List<Theme> getAll() {
        return themeRepository.findAll();
    }

    public Theme getByTitle(final String title) {
        return themeRepository.findByThemeTitle(title);
    }

    public void addTheme(final Theme theme) {
        themeRepository.save(theme);
    }
}
