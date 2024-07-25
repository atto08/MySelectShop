package com.potado.MySelectShop.controller;

import com.potado.MySelectShop.dto.request.FolderRequestDto;
import com.potado.MySelectShop.dto.response.FolderResponseDto;
import com.potado.MySelectShop.security.UserDetailsImpl;
import com.potado.MySelectShop.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FolderController {

    private final FolderService folderService;

    @PostMapping("/folders")
    public void addFolders(@RequestBody FolderRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

        List<String> folderNames = requestDto.getFolderNames();
        folderService.addFolders(folderNames, userDetails.getUser());
    }

    @GetMapping("/folders")
    public List<FolderResponseDto> getFolders(@AuthenticationPrincipal UserDetailsImpl userDetails){

        return folderService.getFolders(userDetails.getUser());
    }
}
