package com.nhat.structurizebe.services;

import com.nhat.structurizebe.models.documents.AccountDocument;
import com.nhat.structurizebe.models.documents.StructureCommentDocument;
import com.nhat.structurizebe.repositories.StructureCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StructureCommentService {

    private final StructureCommentRepository structureCommentRepository;
    private final AuthService authService;

    public void createStructureComment(String structureId, String content, String jwt) throws UsernameNotFoundException {
        AccountDocument account = authService.getAccountByJwt(jwt);
        StructureCommentDocument structureComment = new StructureCommentDocument();
        structureComment.setStructureId(structureId);
        structureComment.setAccountId(account.getId());
        structureComment.setContent(content);
        structureCommentRepository.save(structureComment);
    }
}
