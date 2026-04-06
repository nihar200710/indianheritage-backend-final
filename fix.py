import os
import re

model_dir = r"c:\Users\sai nihar\.vscode\indianheritage\sanchari-backend\src\main\java\com\sanchari\backend\model"

for m in os.listdir(model_dir):
    if not m.endswith('.java'): continue
    with open(os.path.join(model_dir, m), 'r', encoding='utf-8') as f:
        c = f.read()

    # Remove the old string user if it exists and we added a new User user
    if 'private String user;' in c and 'private User user;' in c:
        c = c.replace('private String user;\n', '')
        
    # Fix bad imports
    c = c.replace('import jakarta.persistence.ManyToOne\n', 'import jakarta.persistence.ManyToOne;\n')
    c = c.replace('import jakarta.persistence.JoinColumn\n', 'import jakarta.persistence.JoinColumn;\n')
    c = c.replace('import java.time.LocalDateTime\n', 'import java.time.LocalDateTime;\n')
    c = c.replace('import org.hibernate.annotations.CreationTimestamp\n', 'import org.hibernate.annotations.CreationTimestamp;\n')

    # Revert if it's completely messed up. Wait it's better.
    with open(os.path.join(model_dir, m), 'w', encoding='utf-8') as f:
        f.write(c)

print("Fixes applied.")
