package service;

import entity.Part;
import org.springframework.stereotype.Service;
import repository.PartMapper;

import java.util.List;

@Service
public class PartService {
    private PartMapper partMapper;

    public PartService(PartMapper partMapper) {
        this.partMapper = partMapper;
    }

    // 部品の一覧検索
    public List<Part> findAll() {
        return partMapper.findAll();
    }

    // 部品の種類別検索
    public List<Part> findByPartName(String partName) {
        return partMapper.findByPartName(partName);
    }

}
