package com.sandhya.ai_issue_tracker;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class IssueController {

    private final IssueRepository issueRepository;

    public IssueController(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    // CREATE ISSUE
    @PostMapping("/issues")
    public Issue createIssue(@RequestBody Issue issue) {

        if (issue.getDescription().contains("login")) {
            issue.setPriority("HIGH");
        } else {
            issue.setPriority("LOW");
        }

        return issueRepository.save(issue);
    }

    // GET ALL ISSUES
    @GetMapping("/issues")
    public List<Issue> getIssues() {
        return issueRepository.findAll();
    }

    // GET ISSUE BY ID
    @GetMapping("/issues/{id}")
    public Issue getIssueById(@PathVariable Long id) {
        return issueRepository.findById(id).orElse(null);
    }

    // UPDATE ISSUE
    @PutMapping("/issues/{id}")
    public Issue updateIssue(@PathVariable Long id, @RequestBody Issue updatedIssue) {

        Issue issue = issueRepository.findById(id).orElse(null);

        if (issue != null) {
            issue.setTitle(updatedIssue.getTitle());
            issue.setDescription(updatedIssue.getDescription());

            if (updatedIssue.getDescription().contains("login")) {
                issue.setPriority("HIGH");
            } else {
                issue.setPriority("LOW");
            }

            return issueRepository.save(issue);
        }

        return null;
    }

    // DELETE ISSUE
    @DeleteMapping("/issues/{id}")
    public void deleteIssue(@PathVariable Long id) {
        issueRepository.deleteById(id);
    }
}