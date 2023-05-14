```shell
# 创建活动
http POST localhost:5005/events/RHCP tickets:=10
http POST localhost:5005/events/DjMadlib tickets:=15

# 请求所有活动的列表
http GET localhost:5005/events

# 买两张RHCP的票
http POST localhost:5005/events/RHCP/tickets tickets:=2


```





Heroku deployment
=================

Heroku normally expects the project to reside in the root of the git repo.
the source code for the up and running chapter is not in the root of the repo, so you need to use a different command to deploy to heroku:

    git subtree push --prefix chapter-up-and-running heroku master

This command has to be executed from the root of the git repo, not from within the chapter directory.
The git subtree command is not as featured as the normal push command, for instance, it does not provide a flag to force push,
and it does not support the <local-branch>:<remote-branch> syntax which you can use with git push:

    git push heroku my-localbranch:master

Which is normally used to deploy from a branch to heroku (pushing a branch to heroku master).
It is possible to nest commands though, so if you want to push from a branch you can do the following:

    git push heroku `git subtree split --prefix chapter-up-and-running my-local-branch`:master

Where *my-local-branch* is your local branch.
Forcing a push can be done by nesting commands as well:

    git push heroku `git subtree split --prefix chapter-up-and-running master`:master --force

The above pushes the changes in local master to heroku master.


