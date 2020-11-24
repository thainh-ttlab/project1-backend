package com.project1.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Map<String, Object> createResponse(Page<?> page, List<?> lst) {
        Map<String, Object> response = new HashMap<>();
        response.put("items", lst);
        response.put("totalItems", page.getTotalElements());
        return response;
    }

    public Map<String, Object> findAll(String keyword, Integer page, Integer limit, String order, String orderBy) {
        Sort sort;
        if(order.equals("ASC")) {
            sort = Sort.by(orderBy).ascending();
        } else {
            sort = Sort.by(orderBy).descending();
        }
        Pageable pageable = PageRequest.of(page, limit, sort);
        Page<User> users = userRepository.findUser(keyword, pageable);
        List<User> results = users.getContent();
        return createResponse(users, results);
    }

    public Optional<User> findById(Long id) {
        Optional<User> results = userRepository.findById(id);
        return results;
    }

    public User save(User stock) {
        return userRepository.save(stock);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
