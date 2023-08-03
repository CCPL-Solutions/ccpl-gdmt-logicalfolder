package com.colegiominayticha.ccplgdmtlogicalfolder.dataprovider.jpa.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "logical_folders")
public class LogicalFolderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_user")
    private String createdUser;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_user")
    private String updatedUser;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "id_parent_folder_fk")
    private LogicalFolderEntity parent;

    @OneToMany(mappedBy = "logicalFolder",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    private List<SpecificMetadataEntity> specificMetadata;

    @OneToMany(mappedBy = "logicalFolder",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    private List<ChildrenEntity> children;

    public void addChildren(ChildrenEntity children) {
        if (this.children == null)
            this.children = new ArrayList<>();
        this.children.add(children);
        children.setLogicalFolder(this);
    }

    public void addSpecificMetadata(SpecificMetadataEntity specificMetadataEntity) {
        if (specificMetadata == null)
            specificMetadata = new ArrayList<>();
        specificMetadata.add(specificMetadataEntity);
        specificMetadataEntity.setLogicalFolder(this);
    }

}
