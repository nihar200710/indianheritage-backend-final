import os
import re

model_dir = r"c:\Users\sai nihar\.vscode\indianheritage\sanchari-backend\src\main\java\com\sanchari\backend\model"

models_to_process = [
    "Article.java",
    "ForumThread.java",
    "GuideApplication.java",
    "GuideEarning.java",
    "GuideQuery.java",
    "GuideTour.java",
    "Monument.java",
    "ThreadMessage.java",
    "UserReport.java"
]

imports = """import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
"""

fields = """
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
"""

for model in models_to_process:
    path = os.path.join(model_dir, model)
    with open(path, "r", encoding="utf-8") as f:
        content = f.read()

    # Add imports if not present
    if "import jakarta.persistence.ManyToOne" not in content:
        # insert after the first jakarta import
        content = re.sub(r'(import jakarta\.persistence\.[^;]+;\n)', r'\1' + imports, content, count=1)
        
    # Adding Column if not present
    if "import jakarta.persistence.Column;" not in content:
        content = re.sub(r'(import jakarta\.persistence\.[^;]+;\n)', r'\1import jakarta.persistence.Column;\n', content, count=1)

    # Add default PENDING if status field exists
    content = re.sub(r'private String status;\n', r'private String status = "PENDING";\n', content)
    content = re.sub(r'private String status\s*=\s*"[^"]+";\n', r'private String status = "PENDING";\n', content)

    # Add fields
    if "private LocalDateTime createdAt;" not in content:
        # insert before the last brace
        content = re.sub(r'\n}\s*$', '\n' + fields + '\n}\n', content)
        
    with open(path, "w", encoding="utf-8") as f:
        f.write(content)

print("Done")
