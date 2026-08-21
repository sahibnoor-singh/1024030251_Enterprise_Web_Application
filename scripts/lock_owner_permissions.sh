#!/usr/bin/env bash
set -euo pipefail

repo_root="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

find "$repo_root" -path "$repo_root/.git" -prune -o -type d -exec chmod 755 {} +
find "$repo_root" -path "$repo_root/.git" -prune -o -type f -exec chmod 644 {} +

echo "Applied owner-only write permissions to repository files and directories."
