package com.colegiominayticha.ccplgdmtlogicalfolder.dataprovider.jpa.repository;

import com.colegiominayticha.ccplgdmtlogicalfolder.dataprovider.jpa.entity.ChildrenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IChildrenRepository extends JpaRepository<ChildrenEntity, UUID> {
}
