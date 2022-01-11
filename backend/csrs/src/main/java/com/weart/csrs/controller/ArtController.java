package com.weart.csrs.controller;

import com.weart.csrs.dto.ArtCreateRequestDto;
import com.weart.csrs.dto.ArtResponeDto;
import com.weart.csrs.service.ArtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ArtController {
    private final ArtService artService;

    @PostMapping("api/art/save")
    public Long artSave(@RequestBody ArtCreateRequestDto artCreateRequestDto) {
        return artService.createArt(artCreateRequestDto);
    }

    @GetMapping("api/arts/{artId}")
    public ArtResponeDto selectArt(@PathVariable Long artId) {
        return artService.selectArt(artId);
    }

    @GetMapping("api/arts/list")
    public List<ArtResponeDto> selectAllArts() {
        return artService.selectAll();
    }

    @PutMapping("api/arts/{artId}")
    public Long artUpdate(@PathVariable Long artId, @RequestBody ArtCreateRequestDto artCreateRequestDto) {
        log.info("update art");
        return artService.updateArt(artId, artCreateRequestDto);
    }

    @DeleteMapping("api/arts/{artId}")
    public Long deleteArt(@PathVariable Long artId) {
        artService.deleteArt(artId);
        return artId;
    }
}