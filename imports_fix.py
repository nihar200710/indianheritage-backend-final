import os
model_dir = r"c:\Users\sai nihar\.vscode\indianheritage\sanchari-backend\src\main\java\com\sanchari\backend\model"
req_imports = [
  'import jakarta.persistence.ManyToOne;',
  'import jakarta.persistence.JoinColumn;',
  'import jakarta.persistence.Column;',
  'import java.time.LocalDateTime;',
  'import org.hibernate.annotations.CreationTimestamp;'
]
for m in os.listdir(model_dir):
    if not m.endswith('.java'): continue
    
    # User.java should probably NOT have a ManyToOne mapping to User itself unless we want a self-reference.
    if m == 'User.java': continue
    
    path = os.path.join(model_dir, m)
    with open(path, 'r', encoding='utf-8') as f:
        c = f.read()
    
    imports_to_add = [i for i in req_imports if i not in c]
    if imports_to_add:
        c = c.replace('import jakarta.persistence.Entity;', '\n'.join(imports_to_add) + '\nimport jakarta.persistence.Entity;')
        with open(path, 'w', encoding='utf-8') as f:
            f.write(c)

print("Imports fixed.")
