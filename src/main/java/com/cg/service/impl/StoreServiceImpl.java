package com.cg.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.cg.entity.Sale;
import com.cg.repository.IStoreRepo;
import com.cg.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@author Deependra
@Service
@Transactional
public class StoreServiceImpl implements IStoreService {

    @Autowired
    private IStoreRepo storeRepository;

    @Override
    public List<com.cg.dto.StorePerformanceDTO> getStorePerformance() {
        return storeRepository.findAll().stream().map(store -> {

            long totalSold = 0;
            long totalOrders = 0;

            if (store.getSales() != null) {
                for (Sale s : store.getSales()) {
                    totalOrders++;
                    totalSold += s.getQty();
                }
            }

            return new com.cg.dto.StorePerformanceDTO(
                    store.getStoreId(),
                    store.getStoreName(),
                    store.getStoreAddress(),
                    store.getCity(),
                    store.getState(),
                    store.getZip(),
                    totalOrders,
                    totalSold
            );

        }).collect(Collectors.toList());
    }
}