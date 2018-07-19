package com.bkolomiets.planarryforum.theme.service;

import com.bkolomiets.planarryforum.core.service.HomeService;
import com.bkolomiets.planarryforum.theme.domain.Theme;
import com.bkolomiets.planarryforum.theme.repository.IThemeRepository;
import com.bkolomiets.planarryforum.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.toIntExact;

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
        if (!themeRepository.findAll().isEmpty()) {
            return getSortedListThemes(themeRepository.findAll());
        }
        return new ArrayList<>();
    }

    public Theme getThemeByTitle(final String title) {
        return themeRepository.findByThemeTitle(title);
    }

    public void addTheme(final String title, final String description) {
        String userName = homeService.getCurrentUserName();

        Theme theme = new Theme(title, description, LocalDate.now().toString());
        theme.setUser(userRepository.findByUsername(userName));

        themeRepository.save(theme);
    }

    public void saveEditTheme(final String title, final String editTheme) {
        Theme oldTheme = getThemeByTitle(title);
        oldTheme.setDescription(editTheme);

        themeRepository.save(oldTheme);
    }

    public void deleteThemeByTitle(final String title) {
        Theme theme = themeRepository.findByThemeTitle(title);

        themeRepository.delete(theme);
    }

    private List<Theme> getSortedListThemes(List<Theme> allThemes) {
        List<Theme> sortedList = new ArrayList<>();
        int minNum = getMinNum(allThemes);
        int maxNum = getMaxNum(allThemes);
        int count = minNum;

        for (int i = minNum; i < (maxNum + 1); i++) {
            int oldCount = count;

            for (Theme theme : allThemes) {
                if (theme.getId() == count) {
                    sortedList.add(theme);
                    count++;
                }
            }
            if (count == oldCount) {
                count++;
            }
        }
        return sortedList;
    }

    private int getMinNum(List<Theme> allThemes) {
        int minNum = toIntExact(allThemes.get(0).getId());

        for (Theme theme : allThemes) {
            if (theme.getId() < minNum) {
                minNum = toIntExact(theme.getId());
            }
        }
        return minNum;
    }

    private int getMaxNum(List<Theme> allThemes) {
        int maxNum = toIntExact(allThemes.get(0).getId());

        for (Theme theme : allThemes) {
            if (theme.getId() > maxNum) {
                maxNum = toIntExact(theme.getId());
            }
        }
        return maxNum;
    }
}
