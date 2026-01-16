package repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PartMapper {

    // 部品一覧検索
    List<Part> findAll();

    // 部品の種類別検索
    List<Part> findByPartName(String partName);
}
