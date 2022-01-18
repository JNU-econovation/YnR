package com.weart.csrs.domain.art;

import com.weart.csrs.domain.BaseTimeEntity;
import com.weart.csrs.domain.member.Member;
import com.weart.csrs.web.dto.ArtCreateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.swing.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Art extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ART_ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "Text", length = 500, nullable = false)
    private String content;

    @Column(nullable = false)
    private Long auctionStartPrice;

    @Column(nullable = false)
    private LocalDateTime auctionStartDate;

    @Column(nullable = false)
    private LocalDateTime auctionEndDate;

    @Transient
    private Boolean isStartBid;

    @Builder
    public Art(String title, Long memberId, String content, Long auctionStartPrice, LocalDateTime auctionStartDate, LocalDateTime auctionEndDate) {
        this.title = title;
        this.content = content;
        this.auctionStartPrice = auctionStartPrice;
        this.auctionStartDate = auctionStartDate;
        this.auctionEndDate = auctionEndDate;
    }

    public void update(ArtCreateRequestDto artCreateRequestDto) {
        this.title = artCreateRequestDto.getTitle();
        this.content = artCreateRequestDto.getContent();
        this.auctionStartPrice = artCreateRequestDto.getAuctionStartPrice();
        this.auctionStartDate = artCreateRequestDto.getAuctionStartDate();
        this.auctionEndDate = artCreateRequestDto.getAuctionEndDate();
    }

    public Boolean checkBidTime(){
        isStartBid = false;
        LocalDateTime currentTime = LocalDateTime.now();
        if(currentTime.isAfter(auctionStartDate) || currentTime.isEqual(auctionStartDate)){
            isStartBid = true;
        }
        return isStartBid;
    }
}
